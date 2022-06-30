package com.hover.stax.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.hover.stax.R
import com.hover.stax.balances.BalancesFragment
import com.hover.stax.databinding.BalanceFragmentContainerBinding
import com.hover.stax.domain.model.FinancialTip
import com.hover.stax.ui.theme.StaxTheme
import com.hover.stax.utils.DateUtils
import timber.log.Timber

@Composable
fun TopBar(showOfflineBadge: Boolean) {
	Row(modifier = Modifier
		.fillMaxWidth()
		.padding(all = dimensionResource(id = R.dimen.margin_13)),
		horizontalArrangement = Arrangement.SpaceBetween) {
		TextWithImageLinear(drawable = R.drawable.stax_logo,
			stringRes = R.string.nav_home,
			modifier = Modifier)
		if (showOfflineBadge) {
			TextWithImageLinear(drawable = R.drawable.ic_internet_off,
				stringRes = R.string.working_offline,
				modifier = Modifier.align(Alignment.CenterVertically))
		}
	}
}

@Composable
fun BonusCard(message: String, onClickedTC: () -> Unit, onClickedTopUp: () -> Unit) {
	val size13 = dimensionResource(id = R.dimen.margin_13)
	val size8 = dimensionResource(id = R.dimen.margin_8)
	Card(modifier = Modifier.padding(all = size13), elevation = 2.dp) {
		Row(modifier = Modifier
			.fillMaxWidth()
			.padding(all = size13)) {
			Column(modifier = Modifier.weight(1f)) {
				Text(text = stringResource(id = R.string.get_rewarded),
					style = MaterialTheme.typography.h3)
				Text(text = message, modifier = Modifier.padding(vertical = size8))
				Text(text = stringResource(id = R.string.tc_apply),
					textDecoration = TextDecoration.Underline,
					color = colorResource(id = R.color.brightBlue),
					modifier = Modifier.clickable(onClick = onClickedTC))
				Text(text = stringResource(id = R.string.top_up),
					color = colorResource(id = R.color.brightBlue),
					style = MaterialTheme.typography.h3,
					modifier = Modifier
						.padding(top = size13)
						.clickable(onClick = onClickedTopUp))
			}
			Image(painter = painterResource(id = R.drawable.ic_bonus),
				contentDescription = stringResource(id = R.string.get_rewarded),
				modifier = Modifier
					.size(70.dp)
					.align(Alignment.CenterVertically))
		}

	}
}

@Composable
fun PrimaryFeatures(
	onSendMoneyClicked: () -> Unit,
	onBuyAirtimeClicked: () -> Unit,
	onBuyGoodsClicked: () -> Unit,
	onPayBillClicked: () -> Unit,
	onRequestMoneyClicked: () -> Unit,
) {
	Row(horizontalArrangement = Arrangement.SpaceEvenly,
		modifier = Modifier
			.padding(horizontal = 13.dp, vertical = 26.dp)
			.fillMaxWidth()) {
		TextWithImageVertical(onItemClick = onSendMoneyClicked,
			drawable = R.drawable.ic_transfer_within_24,
			stringRes = R.string.cta_transfer)
		TextWithImageVertical(onItemClick = onBuyAirtimeClicked,
			drawable = R.drawable.ic_system_upate_24,
			stringRes = R.string.cta_airtime)
		TextWithImageVertical(onItemClick = onBuyGoodsClicked,
			drawable = R.drawable.ic_card,
			stringRes = R.string.cta_merchant)
		TextWithImageVertical(onItemClick = onPayBillClicked,
			drawable = R.drawable.ic_utility,
			stringRes = R.string.cta_paybill)
		TextWithImageVertical(onItemClick = onRequestMoneyClicked,
			drawable = R.drawable.ic_baseline_people_24,
			stringRes = R.string.cta_request)
	}
}

@Composable
private fun FinancialTipCard(tipInterface: FinancialTipClickInterface?,
                             financialTip: FinancialTip) {
	val size13 = dimensionResource(id = R.dimen.margin_13)
	Card(elevation = 2.dp, modifier = Modifier.padding(all = size13)) {
		Row(modifier = Modifier
			.padding(all = size13)
			.clickable { tipInterface?.onTipClicked(null) }) {

			Column(modifier = Modifier.weight(1f)){
				TextWithImageLinear(drawable = R.drawable.ic_tip_of_day,
					stringRes = R.string.tip_of_the_day,
					Modifier.padding(bottom = 5.dp))
				Text(text = financialTip.title,
					style = MaterialTheme.typography.body2,
					textDecoration = TextDecoration.Underline)
				Text(text = financialTip.snippet,
					style = MaterialTheme.typography.body2,
					maxLines = 2,
					overflow = TextOverflow.Ellipsis,
					modifier = Modifier.padding(bottom = size13, top = 3.dp))
				Text(text = stringResource(id = R.string.read_more),
					color = colorResource(id = R.color.brightBlue),
					modifier = Modifier.clickable { tipInterface?.onTipClicked(financialTip.id) })
			}

			Image(painter = painterResource(id = R.drawable.tips_fancy_icon),
				contentDescription = null,
				modifier = Modifier.size(70.dp).align(Alignment.CenterVertically),)
		}
	}
}

interface FinancialTipClickInterface {
	fun onTipClicked(tipId: String?)
}

@Composable
private fun TextWithImageVertical(@DrawableRes drawable: Int,
                                  @StringRes stringRes: Int,
                                  onItemClick: () -> Unit) {
	val size24 = dimensionResource(id = R.dimen.margin_24)
	val size55 = dimensionResource(id = R.dimen.margin_55)
	val blue = colorResource(id = R.color.stax_state_blue)
	Column(modifier = Modifier
		.clickable(onClick = onItemClick)
		.padding(horizontal = 8.dp),
		verticalArrangement = Arrangement.Center) {

		Image(painter = painterResource(id = drawable),
			contentDescription = null,
			Modifier
				.size(size24)
				.align(Alignment.CenterHorizontally)
				.drawBehind {
					drawCircle(radius = this.size.minDimension, color = blue)
				})
		Text(text = stringResource(id = stringRes),
			color = colorResource(id = R.color.offWhite),
			textAlign = TextAlign.Center,
			modifier = Modifier
				.padding(top = size24)
				.width(size55))
	}
}


@Composable
private fun TextWithImageLinear(@DrawableRes drawable: Int,
                                @StringRes stringRes: Int,
                                modifier: Modifier) {
	val margin13 = dimensionResource(id = R.dimen.margin_13)
	Row(horizontalArrangement = Arrangement.Center, modifier = modifier) {
		Image(
			painter = painterResource(id = drawable),
			contentDescription = null,
		)
		Text(text = stringResource(id = stringRes),
			modifier = Modifier
				.padding(horizontal = margin13)
				.align(Alignment.CenterVertically),
			color = colorResource(id = R.color.offWhite))
	}
}


@Composable
fun HomeScreen() {
	val financialTip = FinancialTip(id = "1234",
		title = "Do you want to save money",
		content = "This is a test content here so lets see if its going to use ellipse overflow",
		snippet = "This is a test content here so lets see if its going to use ellipse overflow, with an example here",
		date = DateUtils.todayDate(),
		shareCopy = null,
		deepLink = null)

	StaxTheme {
		Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
			Column {
				TopBar(showOfflineBadge = true)
				BonusCard(message = "Buy at least Ksh 50 airtime on Stax to get 3% or more bonus airtime",
					onClickedTC = {},
					onClickedTopUp = {})
				PrimaryFeatures(onSendMoneyClicked = { },
					onBuyAirtimeClicked = { },
					onBuyGoodsClicked = { },
					onPayBillClicked = { },
					onRequestMoneyClicked = {})

				AndroidViewBinding(BalanceFragmentContainerBinding::inflate) {
					val balanceFragment = fragmentContainerView.getFragment<BalancesFragment>()
					Timber.i("Balance fragment visibility is: ${balanceFragment.isVisible}")
				}
				FinancialTipCard(tipInterface = null, financialTip = financialTip)
			}
		}
	}
}


@Preview
@Composable
fun HomeScreenPreview() {
	val financialTip = FinancialTip(id = "1234",
		title = "Do you want to save money",
		content = "This is a test content here so lets see if its going to use ellipse overflow",
		snippet = "This is a test content here so lets see if its going to use ellipse overflow, with an example here",
		date = DateUtils.todayDate(),
		shareCopy = null,
		deepLink = null)

	StaxTheme {
		Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
			Column {
				TopBar(showOfflineBadge = true)
				BonusCard(message = "Buy at least Ksh 50 airtime on Stax to get 3% or more bonus airtime",
					onClickedTC = {},
					onClickedTopUp = {})
				PrimaryFeatures(onSendMoneyClicked = { },
					onBuyAirtimeClicked = { },
					onBuyGoodsClicked = { },
					onPayBillClicked = { },
					onRequestMoneyClicked = {})

				FinancialTipCard(tipInterface = null, financialTip = financialTip)
			}
		}
	}
}